import { FernApiEditor } from "@fern-fern/api-editor-sdk";
import React from "react";
import { usePackage } from "../context/usePackage";
import { EndpointSidebarItem } from "../endpoints/EndpointSidebarItem";
import { ErrorsSidebarGroup } from "../errors/ErrorsSidebarGroup";
import { TypesSidebarGroup } from "../types/TypesSidebarGroup";
import styles from "./PackageSidebarGroup.module.scss";
import { PackageSidebarItem } from "./PackageSidebarItem";

export declare namespace PackageSidebarGroup {
    export interface Props {
        packageId: FernApiEditor.PackageId;
    }
}

export const PackageSidebarGroup: React.FC<PackageSidebarGroup.Props> = ({ packageId }) => {
    const package_ = usePackage(packageId);

    return (
        <div className={styles.container}>
            <PackageSidebarItem package_={package_}>
                {package_.endpoints.map((endpointId) => (
                    <EndpointSidebarItem key={endpointId} endpointId={endpointId} />
                ))}
                <TypesSidebarGroup package_={package_} />
                <ErrorsSidebarGroup package_={package_} />
            </PackageSidebarItem>
        </div>
    );
};