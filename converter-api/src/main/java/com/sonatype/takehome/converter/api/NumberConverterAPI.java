package com.sonatype.takehome.converter.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "ConverterAPIV1")
@RequestMapping(ConverterUrlMapping.CONVERTER_BASE_PATH)
public interface NumberConverterAPI {

    @ApiOperation(value = "Gets the raw view for the given id.")
    @GetMapping(value = "/numberToWords/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Number successfully converted to words.",
                    response = NumberToWordsResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "It is not a number, the number is out of range or did not receive the parameter"),
            @ApiResponse(
                    code = 500, message = "When an server error occurred")
    })
    ResponseEntity<NumberToWordsResponse> transformNumberToWords(
            @ApiParam(value = "Number to transform.", example = "5237", required = true)
            @PathVariable("number") long number);

}
