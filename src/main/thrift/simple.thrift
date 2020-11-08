namespace java net.prettyrandom.thrift.test
namespace kotlin net.prettyrandom.thrift.test.kt
namespace * test
namespace rb SimpleTest

enum STATUS {
    OK,
    NOT_FOUND = 404
    FOLLOWING_INTEGER
    UKNNOWN = 0xa,
    FOLLOWING_HEX_INTEGER
}

struct Request {
    1: string one
    2: required string two
    3: optional string three
}

struct Response {
    1: string one
    2: required string two
    3: optional string three
}

service TestService {
    Response a(1: Request request)
    Response b(1: required Request request)
    Response c(1: optional Request request)
}