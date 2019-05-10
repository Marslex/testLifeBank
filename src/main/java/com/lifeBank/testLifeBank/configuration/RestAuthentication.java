@Component
public final class RestAuthenticationEntryPoint 
  implements AuthenticationEntryPoint {
 
    @Override
    public void commence(
        final HttpServletRequest request, 
        final HttpServletResponse response, 
        final AuthenticationException authException) throws IOException {
         
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
          "Unauthorized");
    }
}