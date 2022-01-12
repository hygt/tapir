package sttp.tapir.docs.openapi

import io.circe.generic.auto._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import sttp.model.StatusCode
import sttp.tapir.docs.openapi.VerifyYamlOneOfTest._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.openapi.Info
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.tests.ContentNegotiation
import sttp.tapir.tests.OneOfBody.{in_one_of_json_xml_text_out_string, in_string_out_one_of_json_xml_text}
import sttp.tapir.{Codec, CodecFormat, emptyOutput, endpoint, header, oneOfDefaultVariant, oneOfVariant, plainBody, statusCode}

class VerifyYamlOneOfBodyTest extends AnyFunSuite with Matchers {
  test("should support input one-of-bodies") {
    val expectedYaml = load("oneOfBody/expected_in_json_xml_text.yml")

    val e = in_one_of_json_xml_text_out_string

    val actualYaml = OpenAPIDocsInterpreter().toOpenAPI(e, Info("test", "1.0")).toYaml
    val actualYamlNoIndent = noIndentation(actualYaml)

    actualYamlNoIndent shouldBe expectedYaml
  }

  test("should support output one-of-bodies") {
    val expectedYaml = load("oneOfBody/expected_out_json_xml_text.yml")

    val e = in_string_out_one_of_json_xml_text

    val actualYaml = OpenAPIDocsInterpreter().toOpenAPI(e, Info("test", "1.0")).toYaml
    println(actualYaml)
    val actualYamlNoIndent = noIndentation(actualYaml)

    actualYamlNoIndent shouldBe expectedYaml
  }
}
