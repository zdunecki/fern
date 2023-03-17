import { AbsoluteFilePath, join } from "@fern-api/fs-utils";
import { createMigrationTester } from "../../../__test__/utils/runFixtureThroughMigration";
import { V10_TO_V9_MIGRATION } from "../migrateFromV10ToV9";

const runMigration = createMigrationTester(V10_TO_V9_MIGRATION);

describe("migrateFromV10ToV9", () => {
    it("add top-level docs to service", async () => {
        const migrated = await runMigration({
            pathToFixture: join(AbsoluteFilePath.of(__dirname), "./fixtures/simple"),
        });
        expect(migrated.services["service_no-docs"]?.docs).toBeUndefined();
        expect(migrated.services["service_only-service-docs"]?.docs).toBe("service docs");
        expect(migrated.services["service_only-top-level-docs"]?.docs).toBe("top-level docs");
        expect(migrated.services["service_both-docs"]?.docs).toBe("top-level docs");
    });
});