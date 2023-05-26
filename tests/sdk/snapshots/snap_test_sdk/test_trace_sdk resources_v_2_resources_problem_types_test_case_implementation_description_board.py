# This file was auto-generated by Fern from our API Definition.

from __future__ import annotations

import typing

import pydantic
import typing_extensions

from .parameter_id import ParameterId


class TestCaseImplementationDescriptionBoard_Html(pydantic.BaseModel):
    type: typing_extensions.Literal["html"]
    value: str

    class Config:
        frozen = True


class TestCaseImplementationDescriptionBoard_ParamId(pydantic.BaseModel):
    type: typing_extensions.Literal["paramId"]
    value: ParameterId

    class Config:
        frozen = True


TestCaseImplementationDescriptionBoard = typing.Union[
    TestCaseImplementationDescriptionBoard_Html, TestCaseImplementationDescriptionBoard_ParamId
]
