package com.forestry.config.Security;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        if (
            req.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || 
            req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
        ) {
            ObjectMapper objectMapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;

            try (InputStream inputStream = req.getInputStream()) {
                Map<String, String> authenticationBean = objectMapper.readValue(inputStream, Map.class);
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    authenticationBean.get("username"), 
                    authenticationBean.get("password")
                );
            } 
            catch(IOException e) {
                e.printStackTrace();
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("", "");
            } 
            finally {
                setDetails(req, usernamePasswordAuthenticationToken);
                return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
            }
        }
        else {
            return super.attemptAuthentication(req, res);
        }
    }
}