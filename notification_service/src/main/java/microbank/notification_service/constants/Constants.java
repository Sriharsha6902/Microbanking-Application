package microbank.notification_service.constants;

public class Constants {

    public static final String MAIL_DEMO_TEXT_HTML =
            """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <title>Email Verification</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                background-color: #f4f4f4;
                                text-align: center;
                                margin: 0;
                                padding: 0;
                            }
                    
                            .container {
                                max-width: 600px;
                                margin: 0 auto;
                                padding: 20px;
                                background-color: #fff;
                                border-radius: 5px;
                                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                            }
                    
                            h1 {
                                color: #333;
                            }
                    
                            p {
                                color: #666;
                            }
                    
                            .button-container {
                                margin: 20px 0;
                            }
                    
                            .button {
                                display: inline-block;
                                padding: 10px 20px;
                                background-color: #007BFF;
                                color: #fff;
                                text-decoration: none;
                                border-radius: 5px;
                                transition: background-color 0.3s ease;
                            }
                    
                            .button:hover {
                                background-color: #0056b3;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <h1>Email Verification</h1>
                            <p>To verify your email, please click on the button below:</p>
                            <div class="button-container">
                                <a class="button" href="%s">Click to Verify</a>
                            </div>
                        </div>
                    </body>
                    </html>
                    """;
}
