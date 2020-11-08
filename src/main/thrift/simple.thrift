namespace java net.prettyrandom.thrift.test
namespace kotlin net.prettyrandom.thrift.test.kt
namespace * test
namespace rb SimpleTest

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