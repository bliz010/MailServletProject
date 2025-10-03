What it does

Accepts POST params: email, subject, message

Sends an email via SMTP (Gmail by default; Outlook settings included)

Returns a simple success message to the browser

Project layout
src/
  main/
    java/
      MailServlet.java
    webapp/
      index.html                # optional test form
      WEB-INF/
        web.xml                 # servlet mapping (if not using @WebServlet)
        lib/                    # runtime jars (kept in repo)
          jakarta.mail-2.0.1.jar
          jakarta.activation-2.0.1.jar


Do not add a servlet-api/jakarta-servlet jar—Tomcat provides it.

Prerequisites

JDK 17

Tomcat 10.x

Jars in WEB-INF/lib/:

jakarta.mail-2.0.1.jar

jakarta.activation-2.0.1.jar

Configure credentials (keep secrets out of code)

The servlet uses placeholders; supply real values via environment variables:
