# Thrift parser and generator in kotlin
This project aims to build a pure kotlin implementation of thrift including a parser, client generator and server stub generator.

[![Actions Status](https://github.com/TobiasBales/thrift-kotlin/workflows/CI/badge.svg)](https://github.com/TobiasBales/thrift-kotlin/actions)

## Current status
This project is currently work in progress.

At the moment here is just the parser, and it handles ~80% of thrift definitions.
The next steps will be to get basic client generation working and then to implement the missing features into the parser.

## Why another thrift parser?
Using the java implementation of thrift clients and stubs does not work well with reactive stacks
and implementing a new language in the c++ codebase of thrift is cumbersome,
might not provide all the features we would like (e.g. having a parser to generate a
[graphql playground](https://github.com/graphql/graphql-playground) like web ui to make requests).
On top of that getting such significant changes added to the mainline thrift project might take a lot of time.

Looking at the great [typescript implementation](https://github.com/creditkarma/thrift-typescript) by creditkarma this
seems like a project that might work out given some time.

There is another kotlin thrift implementation called [thrifty](https://github.com/microsoft/thrifty)
which targets android and thus is lacking some features we are looking for (e.g. server stubs)
and also makes some trade-offs that seem undesirable for server-to-server communication. 