import classNames from "classnames";
import { PropsWithChildren } from "react";
import styles from "./CenteredContent.module.scss";

export declare namespace CenteredContent {
    export type Props = PropsWithChildren<{
        // the outer container
        containerClassName?: string;
        // an inner wrapper. this has no margin/padding applied, so this is the
        // only div you can safely add margin or padding to
        wrapperClassName?: string;
        // the inner container
        className?: string;

        scrollable?: boolean;
        withVerticalPadding?: boolean;
        fill?: boolean;
    }>;
}

export const CenteredContent: React.FC<CenteredContent.Props> = ({
    className,
    containerClassName,
    wrapperClassName,
    withVerticalPadding = false,
    fill = false,
    scrollable = false,
    children,
}) => {
    return (
        <div
            className={classNames(containerClassName, styles.container, {
                [styles.fill ?? "_"]: fill,
                [styles.scrollable ?? "_"]: scrollable,
            })}
        >
            <div className={classNames(wrapperClassName, styles.wrapper)}>
                <div className={styles.content}>
                    <div
                        className={classNames(className, styles.inner, {
                            [styles.withVerticalPadding ?? "_"]: withVerticalPadding,
                        })}
                    >
                        {children}
                    </div>
                </div>
            </div>
        </div>
    );
};