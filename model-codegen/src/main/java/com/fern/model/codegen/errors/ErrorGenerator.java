/*
 * (c) Copyright 2022 Birch Solutions Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fern.model.codegen.errors;

import com.fern.codegen.GeneratedError;
import com.fern.codegen.GeneratedInterface;
import com.fern.codegen.Generator;
import com.fern.codegen.GeneratorContext;
import com.fern.codegen.IGeneratedFile;
import com.fern.codegen.utils.ClassNameUtils.PackageType;
import com.fern.ir.model.declaration.Availability;
import com.fern.ir.model.declaration.AvailabilityStatus;
import com.fern.ir.model.errors.ErrorDeclaration;
import com.fern.ir.model.types.DeclaredTypeName;
import com.fern.ir.model.types.TypeDeclaration;
import com.fern.java.exception.HttpException;
import com.fern.model.codegen.TypeDefinitionGenerator;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.util.Map;
import javax.lang.model.element.Modifier;

public final class ErrorGenerator extends Generator {
    public static final String GET_STATUS_CODE_METHOD_NAME = "getStatusCode";
    public static final String GET_ERROR_BODY_METHOD_NAME = "getBody";
    private static final String BODY_SUFFIX = "Body";
    private static final String BODY_FIELD_NAME = "body";
    private final ErrorDeclaration errorDeclaration;
    private final ClassName errorClassName;
    private final GeneratorContext generatorContext;
    private final Map<DeclaredTypeName, GeneratedInterface> generatedInterfaces;

    public ErrorGenerator(
            ErrorDeclaration errorDeclaration,
            GeneratorContext generatorContext,
            Map<DeclaredTypeName, GeneratedInterface> generatedInterfaces) {
        super(generatorContext);
        this.errorDeclaration = errorDeclaration;
        this.generatorContext = generatorContext;
        this.generatedInterfaces = generatedInterfaces;
        this.errorClassName = generatorContext
                .getClassNameUtils()
                .getClassNameFromErrorName(errorDeclaration.getName(), PackageType.MODEL);
    }

    @Override
    public GeneratedError generate() {
        DeclaredTypeName declaredTypeName = DeclaredTypeName.builder()
                .fernFilepath(errorDeclaration.getName().getFernFilepath())
                .fernFilepathV2(errorDeclaration.getName().getFernFilepathV2())
                .name(errorClassName.simpleName() + BODY_SUFFIX)
                .nameV2(errorDeclaration.getName().getNameV2())
                .nameV3(errorDeclaration.getName().getNameV3())
                .build();
        ClassName className = generatorContext
                .getClassNameUtils()
                .getClassNameFromDeclaredTypeName(declaredTypeName, PackageType.MODEL);
        IGeneratedFile generatedBodyFile = errorDeclaration
                .getType()
                .visit(new TypeDefinitionGenerator(
                        TypeDeclaration.builder()
                                // TODO(dsinghvi): don't use a dummy availability
                                .availability(Availability.builder()
                                        .status(AvailabilityStatus.GENERAL_AVAILABILITY)
                                        .build())
                                .name(declaredTypeName)
                                .shape(errorDeclaration.getType())
                                .build(),
                        generatorContext,
                        generatedInterfaces,
                        className));
        ClassName bodyClassName = generatedBodyFile.className();
        TypeSpec.Builder errorTypeSpecBuilder = TypeSpec.classBuilder(errorClassName)
                .superclass(ClassName.get(HttpException.class))
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(FieldSpec.builder(generatedBodyFile.className(), BODY_FIELD_NAME)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(generatedBodyFile.className(), BODY_FIELD_NAME)
                        .addStatement("this.$L = $L", BODY_FIELD_NAME, BODY_FIELD_NAME)
                        .build())
                .addMethod(MethodSpec.methodBuilder(GET_ERROR_BODY_METHOD_NAME)
                        .addModifiers(Modifier.PUBLIC)
                        .returns(bodyClassName)
                        .addStatement("return $L", BODY_FIELD_NAME)
                        .build());
        if (errorDeclaration.getHttp().isPresent()) {
            errorTypeSpecBuilder.addMethod(MethodSpec.methodBuilder(GET_STATUS_CODE_METHOD_NAME)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("return $L", errorDeclaration.getHttp().get().getStatusCode())
                    .returns(ClassName.INT)
                    .addAnnotation(Override.class)
                    .build());
        }
        JavaFile errorFile = JavaFile.builder(errorClassName.packageName(), errorTypeSpecBuilder.build())
                .build();
        return GeneratedError.builder()
                .file(errorFile)
                .className(errorClassName)
                .errorDeclaration(errorDeclaration)
                .generatedBodyFile(generatedBodyFile)
                .build();
    }
}
