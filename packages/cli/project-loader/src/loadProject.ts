import { AbsoluteFilePath, doesPathExist, join, RelativeFilePath } from "@fern-api/fs-utils";
import { APIS_DIRECTORY, FERN_DIRECTORY, getFernDirectory, loadProjectConfig } from "@fern-api/project-configuration";
import { TaskContext } from "@fern-api/task-context";
import { APIWorkspace, loadAPIWorkspace, loadDocsWorkspace } from "@fern-api/workspace-loader";
import chalk from "chalk";
import { readdir } from "fs/promises";
import { handleFailedWorkspaceParserResult } from "./handleFailedWorkspaceParserResult";
import { Project } from "./Project";

export declare namespace loadProject {
    export interface Args {
        cliName: string;
        cliVersion: string;
        commandLineApiWorkspace: string | undefined;
        /**
         * if false and commandLineWorkspace it not defined,
         * loadProject will cause the CLI to fail
         */
        defaultToAllApiWorkspaces: boolean;
        context: TaskContext;

        local?: boolean | undefined;
    }
}

export async function loadProject({
    cliName,
    cliVersion,
    commandLineApiWorkspace,
    defaultToAllApiWorkspaces,
    context,
    local
}: loadProject.Args): Promise<Project> {
    const fernDirectory = await getFernDirectory({
        local
    });
    if (fernDirectory == null) {
        return context.failAndThrow(`Directory "${FERN_DIRECTORY}" not found.`);
    }

    const apiWorkspaces = await loadApis({
        cliName,
        fernDirectory,
        cliVersion,
        context,
        commandLineApiWorkspace,
        defaultToAllApiWorkspaces
    });

    return {
        config: await loadProjectConfig({ directory: fernDirectory, context, local }),
        apiWorkspaces,
        docsWorkspaces: await loadDocsWorkspace({ fernDirectory, context })
    };
}

export async function loadApis({
    cliName,
    fernDirectory,
    context,
    cliVersion,
    commandLineApiWorkspace,
    defaultToAllApiWorkspaces
}: {
    cliName: string;
    fernDirectory: AbsoluteFilePath;
    context: TaskContext;
    cliVersion: string;
    commandLineApiWorkspace: string | undefined;
    defaultToAllApiWorkspaces: boolean;
}): Promise<APIWorkspace[]> {
    const apisDirectory = join(fernDirectory, RelativeFilePath.of(APIS_DIRECTORY));
    const apisDirectoryExists = await doesPathExist(apisDirectory);
    if (apisDirectoryExists) {
        const apiDirectoryContents = await readdir(apisDirectory, { withFileTypes: true });

        const apiWorkspaceDirectoryNames = apiDirectoryContents.reduce<string[]>((all, item) => {
            if (item.isDirectory()) {
                all.push(item.name);
            }
            return all;
        }, []);

        if (commandLineApiWorkspace != null) {
            if (!apiWorkspaceDirectoryNames.includes(commandLineApiWorkspace)) {
                return context.failAndThrow("API does not exist: " + commandLineApiWorkspace);
            }
        } else if (apiWorkspaceDirectoryNames.length === 0) {
            return context.failAndThrow("No APIs found.");
        } else if (apiWorkspaceDirectoryNames.length > 1 && !defaultToAllApiWorkspaces) {
            let message = "There are multiple workspaces. You must specify one with --api:\n";
            const longestWorkspaceName = Math.max(
                ...apiWorkspaceDirectoryNames.map((workspaceName) => workspaceName.length)
            );
            message += apiWorkspaceDirectoryNames
                .map((workspaceName) => {
                    const suggestedCommand = `${cliName} ${process.argv.slice(2).join(" ")} --api ${workspaceName}`;
                    return ` › ${chalk.bold(workspaceName.padEnd(longestWorkspaceName))}  ${chalk.dim(
                        suggestedCommand
                    )}`;
                })
                .join("\n");
            return context.failAndThrow(message);
        }

        const apiWorkspaces: APIWorkspace[] = [];

        const filteredWorkspaces =
            commandLineApiWorkspace != null
                ? apiWorkspaceDirectoryNames.filter((api) => {
                      return api === commandLineApiWorkspace;
                  })
                : apiWorkspaceDirectoryNames;

        await Promise.all(
            filteredWorkspaces.map(async (workspaceDirectoryName) => {
                const workspace = await loadAPIWorkspace({
                    absolutePathToWorkspace: join(apisDirectory, RelativeFilePath.of(workspaceDirectoryName)),
                    context,
                    cliVersion,
                    workspaceName: workspaceDirectoryName
                });
                if (workspace.didSucceed) {
                    apiWorkspaces.push(workspace.workspace);
                } else {
                    handleFailedWorkspaceParserResult(workspace, context.logger);
                    context.failAndThrow();
                }
            })
        );

        return apiWorkspaces;
    }

    const workspace = await loadAPIWorkspace({
        absolutePathToWorkspace: fernDirectory,
        context,
        cliVersion,
        workspaceName: undefined
    });
    if (workspace.didSucceed) {
        return [workspace.workspace];
    } else {
        handleFailedWorkspaceParserResult(workspace, context.logger);
        return [];
    }
}
