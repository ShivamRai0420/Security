| **JWT Authentication (Stateless)**                                                                | **Basic Authentication (Stateful)**                                                         |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| 1️⃣ Client sends **POST /auth/login** with `{username, password}`                                 | 1️⃣ Client sends request with header:<br>`Authorization: Basic <base64(username:password)>` |
| 2️⃣ `AuthenticationManager → DaoAuthenticationProvider → MyUserDetailsService` authenticates user | 2️⃣ `BasicAuthenticationFilter` extracts credentials                                        |
| 3️⃣ Password checked with `BCryptPasswordEncoder`                                                 | 3️⃣ `AuthenticationManager → DaoAuthenticationProvider → MyUserDetailsService` loads user   |
| 4️⃣ On success → `JwtUtil.generateToken()` creates JWT (sub, role, iat, exp, signed HS256)        | 4️⃣ Password validated with `BCryptPasswordEncoder`                                         |
| 5️⃣ Token returned in `LoginResponseDto`                                                          | 5️⃣ If valid → authenticated **session** created                                            |
| 6️⃣ Client stores token & sends in header:<br>`Authorization: Bearer <token>`                     | 6️⃣ Spring checks roles before controller executes                                          |
| 7️⃣ `JwtFilter` validates token → sets `SecurityContextHolder`                                    | 7️⃣ If invalid → **401/403**                                                                |
| 8️⃣ Spring checks roles → controller executes                                                     |                                                                                             |
| ❌ Invalid/Expired token → **401/403**                                                                                                                                                      |
