#if canImport(Testing)
import Testing
import Objc2Foundation

@Suite("Objc2Foundation Swift Export Tests")
struct Objc2FoundationExportTests {
    @Test("Objc2Foundation swift module imported cleanly")
    func testSwiftModuleLoads() throws {
        #expect(Bool(true), "Objc2Foundation swift module imported cleanly")
    }
}
#elseif canImport(XCTest)
import XCTest
import Objc2Foundation

final class Objc2FoundationExportTests: XCTestCase {
    func testSwiftModuleLoads() throws {
        XCTAssertTrue(true, "Objc2Foundation swift module imported cleanly")
    }
}
#endif
