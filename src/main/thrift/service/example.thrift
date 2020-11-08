namespace * net.prettyrandom.thrift.test.generic


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
    Response function(1: Request request)
    void voidFunction(1: required Request request)
}