import { AbsoluteFilePath } from "@fern-api/core-utils";
import { TaskContext, TASK_FAILURE } from "@fern-api/task-context";
import { readFile, writeFile } from "fs/promises";
import YAML from "yaml";
import { Migration } from "../../../types/Migration";
import { getAllGeneratorYamlFiles } from "./getAllGeneratorYamlFiles";

export const migration: Migration = {
    name: "add-publishing-to-release-generators",
    summary: "Adds publishing and github keys to release generators configuration",
    run: async ({ context }) => {
        const generatorYamlFiles = await getAllGeneratorYamlFiles(context);
        if (generatorYamlFiles === TASK_FAILURE) {
            return;
        }
        for (const filepath of generatorYamlFiles) {
            try {
                await migrateGeneratorsYml(filepath, context);
            } catch (error) {
                context.fail(`Failed to migrate ${filepath}`, error);
            }
        }
    },
};

async function migrateGeneratorsYml(filepath: AbsoluteFilePath, context: TaskContext): Promise<void> {
    const contents = await readFile(filepath);
    const parsedDocument = YAML.parseDocument(contents.toString());
    const releaseGenerators = parsedDocument.get("release");
    if (releaseGenerators == null) {
        return;
    }
    if (!YAML.isSeq(releaseGenerators)) {
        context.fail(`release generators are not a list in ${filepath}`);
        return;
    }
    releaseGenerators.items.forEach((releaseGenerator) => {
        if (!YAML.isMap(releaseGenerator)) {
            context.fail(`release generator is not an object in ${filepath}`);
            return;
        }
        const outputs = releaseGenerator.get("outputs");
        if (!YAML.isMap(outputs)) {
            context.fail(`outputs is not an object in ${filepath}`);
            return;
        }

        const githubOutput = outputs.get("github");
        if (YAML.isMap(githubOutput) && githubOutput.has("repository")) {
            releaseGenerator.set("github", githubOutput);
        }

        const publishing: Record<string, unknown> = {};

        const npmOutput = outputs.get("npm");
        if (YAML.isMap(npmOutput)) {
            publishing.npm = npmOutput;
        }

        const mavenOutput = outputs.get("maven");
        if (YAML.isMap(mavenOutput)) {
            publishing.maven = mavenOutput;
        }

        if (Object.keys(publishing).length > 0) {
            releaseGenerator.set("publishing", publishing);
        }

        releaseGenerator.delete("outputs");
    });
    await writeFile(filepath, parsedDocument.toString());
}