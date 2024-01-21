import { validateSchema } from "@fern-api/config-management-commons";
import { AbsoluteFilePath, join, RelativeFilePath } from "@fern-api/fs-utils";
import { TaskContext } from "@fern-api/task-context";
import { readFile } from "fs/promises";
import { PROJECT_CONFIG_FILENAME } from "./constants";
import { ProjectConfigSchema } from "./schemas/ProjectConfigSchema";

export interface ProjectConfig {
    _absolutePath: AbsoluteFilePath | null;
    rawConfig: ProjectConfigSchema;
    organization: string;
    version: string;
}

export async function loadProjectConfig({
    directory,
    context,
    local
}: {
    directory: AbsoluteFilePath;
    context: TaskContext;
    local?: boolean | undefined;
}): Promise<ProjectConfig> {
    if (local === true) {
        return {
            _absolutePath: null,
            rawConfig: {
                organization: "",
                version: ""
            },
            organization: "",
            version: ""
        };
    }

    const pathToConfig = join(directory, RelativeFilePath.of(PROJECT_CONFIG_FILENAME));

    const projectConfigStr = await readFile(pathToConfig);
    const projectConfigParsed = JSON.parse(projectConfigStr.toString()) as unknown;
    const rawProjectConfig = await validateSchema({
        schema: ProjectConfigSchema,
        value: projectConfigParsed,
        context,
        filepathBeingParsed: pathToConfig
    });
    return {
        _absolutePath: pathToConfig,
        rawConfig: rawProjectConfig,
        organization: rawProjectConfig.organization,
        version: rawProjectConfig.version
    };
}
