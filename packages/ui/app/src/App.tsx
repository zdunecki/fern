import { FocusStyleManager } from "@blueprintjs/core";
import "@blueprintjs/core/lib/css/blueprint.css";
import "@blueprintjs/icons/lib/css/blueprint-icons.css";
import "@blueprintjs/popover2/lib/css/blueprint-popover2.css";
import "@blueprintjs/select/lib/css/blueprint-select.css";
import "normalize.css";
import { initializePosthog } from "./analytics/posthog";
import styles from "./App.module.scss";
import { AppRoutes } from "./AppRoutes";
import { CONTEXTS } from "./contexts";

FocusStyleManager.onlyShowFocusOnTabs();

// this API key is client-side safe
const POSTHOG_API_KEY = import.meta.env.VITE_POSTHOG_API_KEY;
if (POSTHOG_API_KEY != null) {
    initializePosthog(POSTHOG_API_KEY);
}

export const App: React.FC = () => {
    return (
        <div className={styles.app}>
            {CONTEXTS.reduceRight(
                (children, Context) => (
                    <Context>{children}</Context>
                ),
                <AppRoutes />
            )}
        </div>
    );
};