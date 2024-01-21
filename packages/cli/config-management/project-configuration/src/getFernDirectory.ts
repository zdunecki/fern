import { AbsoluteFilePath, doesPathExist, join, RelativeFilePath } from "@fern-api/fs-utils";
import { findUp } from "find-up";
import { FERN_DIRECTORY, PROJECT_CONFIG_FILENAME } from "./constants";

interface GetFernDirectoryArgs {
    local?: boolean | undefined;
}

export async function getFernDirectory(args?: GetFernDirectoryArgs): Promise<AbsoluteFilePath | undefined> {
    const fernDirectoryStr = await findUp(FERN_DIRECTORY, { type: "directory" });
    if (fernDirectoryStr == null) {
        return undefined;
    }
    const absolutePathToFernDirectory = AbsoluteFilePath.of(fernDirectoryStr);

    if (args && args.local === true) {
        return absolutePathToFernDirectory;
    }

    if (await doesPathExist(join(absolutePathToFernDirectory, RelativeFilePath.of(PROJECT_CONFIG_FILENAME)))) {
        return absolutePathToFernDirectory;
    } else {
        return undefined;
    }
}
