import { EncodeMethod, TsMorph, tsMorph } from "@fern-typescript/helper-utils";
import { HathoraEncoderConstants } from "../constants";
import { generateBinSerdeTypeReference, generateBinSerdeValueReference } from "./bin-serde/generateBinSerdeReference";

export const ENCODE_PARAMETER_NAME = "value";
export const BIN_SERDE_WRITER_VARIABLE_NAME = "writer";
export const BIN_SERDE_READER_VARIABLE_NAME = "reader";

export interface EncodeMethods {
    decodedType: tsMorph.ts.TypeNode;
    encodeBody: tsMorph.ts.Statement[];
    decodeBody: tsMorph.ts.Statement[];
}

export declare namespace constructEncodeMethods {
    export interface Args {
        methods: EncodeMethods;
        ts: TsMorph["ts"];
    }
}

export function constructEncodeMethods({
    methods,
    ts,
}: constructEncodeMethods.Args): tsMorph.ts.ObjectLiteralExpression {
    const PARAMETER_NAME = "maybeReader";

    return ts.factory.createObjectLiteralExpression(
        [
            ts.factory.createMethodDeclaration(
                undefined,
                undefined,
                undefined,
                ts.factory.createIdentifier(EncodeMethod.Encode),
                undefined,
                undefined,
                [
                    ts.factory.createParameterDeclaration(
                        undefined,
                        undefined,
                        undefined,
                        ENCODE_PARAMETER_NAME,
                        undefined,
                        methods.decodedType
                    ),
                    ts.factory.createParameterDeclaration(
                        undefined,
                        undefined,
                        undefined,
                        ts.factory.createIdentifier(BIN_SERDE_WRITER_VARIABLE_NAME),
                        undefined,
                        undefined,
                        ts.factory.createNewExpression(
                            generateBinSerdeValueReference(ts, HathoraEncoderConstants.BinSerDe.Exports.WRITER),
                            undefined,
                            []
                        )
                    ),
                ],
                generateBinSerdeTypeReference(ts, HathoraEncoderConstants.BinSerDe.Exports.WRITER),
                ts.factory.createBlock(methods.encodeBody)
            ),
            ts.factory.createMethodDeclaration(
                undefined,
                undefined,
                undefined,
                ts.factory.createIdentifier(EncodeMethod.Decode),
                undefined,
                undefined,
                [
                    ts.factory.createParameterDeclaration(
                        undefined,
                        undefined,
                        undefined,
                        PARAMETER_NAME,
                        undefined,
                        ts.factory.createUnionTypeNode([
                            ts.factory.createTypeReferenceNode(ts.factory.createIdentifier("Uint8Array")),
                            generateBinSerdeTypeReference(ts, HathoraEncoderConstants.BinSerDe.Exports.READER),
                        ])
                    ),
                ],
                methods.decodedType,
                ts.factory.createBlock([
                    ts.factory.createVariableStatement(
                        undefined,
                        ts.factory.createVariableDeclarationList(
                            [
                                ts.factory.createVariableDeclaration(
                                    ts.factory.createIdentifier(BIN_SERDE_READER_VARIABLE_NAME),
                                    undefined,
                                    undefined,
                                    ts.factory.createConditionalExpression(
                                        ts.factory.createCallExpression(
                                            ts.factory.createPropertyAccessExpression(
                                                ts.factory.createIdentifier("ArrayBuffer"),
                                                ts.factory.createIdentifier("isView")
                                            ),
                                            undefined,
                                            [ts.factory.createIdentifier(PARAMETER_NAME)]
                                        ),
                                        ts.factory.createToken(ts.SyntaxKind.QuestionToken),
                                        ts.factory.createNewExpression(
                                            generateBinSerdeValueReference(
                                                ts,
                                                HathoraEncoderConstants.BinSerDe.Exports.READER
                                            ),
                                            undefined,
                                            [ts.factory.createIdentifier(PARAMETER_NAME)]
                                        ),
                                        ts.factory.createToken(ts.SyntaxKind.ColonToken),
                                        ts.factory.createIdentifier(PARAMETER_NAME)
                                    )
                                ),
                            ],
                            ts.NodeFlags.Const
                        )
                    ),
                    ...methods.decodeBody,
                ])
            ),
        ],
        true
    );
}