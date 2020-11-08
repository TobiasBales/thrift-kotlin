namespace * net.prettyrandom.thrift.test.generic

struct Response {
    1: string one
    2: required i32 two
    3: optional i64 three = 123
}