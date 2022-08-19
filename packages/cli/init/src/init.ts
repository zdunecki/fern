import { AbsoluteFilePath, cwd, join, RelativeFilePath } from "@fern-api/core-utils";
import {
    FERN_DIRECTORY,
    getFernDirectory,
    ProjectConfigSchema,
    PROJECT_CONFIG_FILENAME,
} from "@fern-api/project-configuration";
import { mkdir, writeFile } from "fs/promises";
import { createWorkspace } from "./createWorkspace";

export async function initialize({
    organization,
    latestVersionOfCli,
}: {
    organization: string;
    latestVersionOfCli: string;
}): Promise<void> {
    const existingFernDirectory = await getFernDirectory();
    if (existingFernDirectory != null) {
        throw new Error("Could not initialize fern because a .fern directory already exists: " + existingFernDirectory);
    }

    const fernDirectory = join(cwd(), RelativeFilePath.of(FERN_DIRECTORY));
    await mkdir(FERN_DIRECTORY);
    await writeProjectConfig({
        filepath: join(fernDirectory, RelativeFilePath.of(PROJECT_CONFIG_FILENAME)),
        organization,
        latestVersionOfCli,
    });
    await createWorkspace({
        directoryOfWorkspace: join(fernDirectory, RelativeFilePath.of("api")),
    });
}

async function writeProjectConfig({
    organization,
    filepath,
    latestVersionOfCli,
}: {
    organization: string;
    filepath: AbsoluteFilePath;
    latestVersionOfCli: string;
}): Promise<void> {
    const projectConfig: ProjectConfigSchema = {
        organization,
        version: latestVersionOfCli,
    };
    await writeFile(filepath, JSON.stringify(projectConfig, undefined, 4));
}