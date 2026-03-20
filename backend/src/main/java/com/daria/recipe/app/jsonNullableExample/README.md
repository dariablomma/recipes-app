## About

This folder is only for demonstration purposes. In March 2026, JsonNullable plugin with latest version 0.2.9 is not compatible with Jackson 3.

Proofs of incompatibility, seen from JsonNullable official GitHub https://github.com/OpenAPITools/jackson-databind-nullable
-  old Jackson import
Current import example:  `import com.fasterxml.jackson.databind.ObjectMapper;`
New import should include `tools.jackson`, which is states in official Spring docs - https://spring.io/blog/2025/10/07/introducing-jackson-3-support-in-spring
- outdated `ObjectMapper` usage
In new format it should be `JsonMapper`, quote from official Spring docs : "Jackson 3 introduces a lot of changes and enhancements, but from a Spring perspective one of the most important ones to understand and embrace is the switch from a mutable ObjectMapper in Jackson 2 to an immutable JsonMapper in Jackson 3."

Example was taken from this article - https://kdrozd.pl/how-to-perform-a-partial-update-patch-with-explicit-null/
