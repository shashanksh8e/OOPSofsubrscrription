package com.oopdemo.web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * Simple HTTP Server to expose OOP demo as REST API.
 * This allows frontend applications to interact with our Java OOP classes.
 */
public class SimpleWebServer {
    private HttpServer server;
    private UserController userController;
    private Gson gson;
    
    public SimpleWebServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        userController = new UserController();
        gson = new Gson();
        setupRoutes();
    }
    
    private void setupRoutes() {
        // Serve static files (HTML, CSS, JS)
        server.createContext("/", new StaticFileHandler());
        
        // API endpoints
        server.createContext("/api/users/regular", new CreateRegularUserHandler());
        server.createContext("/api/users/admin", new CreateAdminUserHandler());
        server.createContext("/api/users/list", new GetAllUsersHandler());
        server.createContext("/api/users/polymorphism", new PolymorphismDemoHandler());
        server.createContext("/api/users/notify", new NotifyUserHandler());
        server.createContext("/api/users/admin-action", new AdminActionHandler());
    }
    
    public void start() {
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on http://localhost:" + server.getAddress().getPort());
        System.out.println("Open your browser and go to: http://localhost:" + server.getAddress().getPort());
    }
    
    public void stop() {
        server.stop(0);
    }
    
    // Handler for serving static HTML files
    class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = getIndexHTML();
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    // API Handlers
    class CreateRegularUserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                Map<String, String> params = parseRequestBody(exchange);
                String name = params.get("name");
                String email = params.get("email");
                
                Map<String, Object> result = userController.createRegularUser(name, email);
                sendJsonResponse(exchange, result);
            } else {
                sendErrorResponse(exchange, "Method not allowed");
            }
        }
    }
    
    class CreateAdminUserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                Map<String, String> params = parseRequestBody(exchange);
                String name = params.get("name");
                String email = params.get("email");
                
                Map<String, Object> result = userController.createAdminUser(name, email);
                sendJsonResponse(exchange, result);
            } else {
                sendErrorResponse(exchange, "Method not allowed");
            }
        }
    }
    
    class GetAllUsersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                Map<String, Object> result = userController.getAllUsers();
                sendJsonResponse(exchange, result);
            } else {
                sendErrorResponse(exchange, "Method not allowed");
            }
        }
    }
    
    class PolymorphismDemoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                Map<String, Object> result = userController.demonstratePolymorphism();
                sendJsonResponse(exchange, result);
            } else {
                sendErrorResponse(exchange, "Method not allowed");
            }
        }
    }
    
    class NotifyUserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                Map<String, String> params = parseRequestBody(exchange);
                String userId = params.get("userId");
                String message = params.get("message");
                
                Map<String, Object> result = userController.sendNotification(userId, message);
                sendJsonResponse(exchange, result);
            } else {
                sendErrorResponse(exchange, "Method not allowed");
            }
        }
    }
    
    class AdminActionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                Map<String, String> params = parseRequestBody(exchange);
                String adminId = params.get("adminId");
                String targetUserId = params.get("targetUserId");
                String action = params.get("action");
                
                Map<String, Object> result = userController.demonstrateAdminFunctionality(adminId, targetUserId, action);
                sendJsonResponse(exchange, result);
            } else {
                sendErrorResponse(exchange, "Method not allowed");
            }
        }
    }
    
    // Helper methods
    private Map<String, String> parseRequestBody(HttpExchange exchange) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        
        Map<String, String> params = new HashMap<>();
        String[] pairs = body.toString().split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                params.put(URLDecoder.decode(keyValue[0], "UTF-8"), 
                          URLDecoder.decode(keyValue[1], "UTF-8"));
            }
        }
        return params;
    }
    
    private void sendJsonResponse(HttpExchange exchange, Object data) throws IOException {
        String response = gson.toJson(data);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    private void sendErrorResponse(HttpExchange exchange, String error) throws IOException {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", error);
        sendJsonResponse(exchange, errorResponse);
    }
    
    private String getIndexHTML() {
        return "<!DOCTYPE html>\n" +
               "<html>\n" +
               "<head>\n" +
               "    <title>Java OOP Demo - Web Interface</title>\n" +
               "    <style>\n" +
               "        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }\n" +
               "        .container { max-width: 1200px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }\n" +
               "        h1 { color: #333; text-align: center; }\n" +
               "        .section { margin: 20px 0; padding: 15px; border: 1px solid #ddd; border-radius: 5px; }\n" +
               "        .section h3 { color: #555; margin-top: 0; }\n" +
               "        input, button, select { padding: 8px; margin: 5px; border: 1px solid #ccc; border-radius: 4px; }\n" +
               "        button { background-color: #007bff; color: white; cursor: pointer; }\n" +
               "        button:hover { background-color: #0056b3; }\n" +
               "        .result { margin-top: 10px; padding: 10px; background-color: #f8f9fa; border-radius: 4px; }\n" +
               "        .user-card { background: #e9ecef; padding: 10px; margin: 5px 0; border-radius: 4px; }\n" +
               "        .success { color: #28a745; }\n" +
               "        .error { color: #dc3545; }\n" +
               "    </style>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <div class='container'>\n" +
               "        <h1>Java OOP Demonstration - Web Interface</h1>\n" +
               "        <p>This web interface demonstrates how Java OOP concepts can be accessed through a web frontend.</p>\n" +
               "        \n" +
               "        <div class='section'>\n" +
               "            <h3>1. Create Users (Inheritance Demo)</h3>\n" +
               "            <div>\n" +
               "                <input type='text' id='userName' placeholder='User Name' />\n" +
               "                <input type='email' id='userEmail' placeholder='User Email' />\n" +
               "                <button onclick='createRegularUser()'>Create Regular User</button>\n" +
               "                <button onclick='createAdminUser()'>Create Admin User</button>\n" +
               "            </div>\n" +
               "            <div id='createResult' class='result'></div>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='section'>\n" +
               "            <h3>2. View All Users</h3>\n" +
               "            <button onclick='loadUsers()'>Load All Users</button>\n" +
               "            <div id='usersList' class='result'></div>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='section'>\n" +
               "            <h3>3. Polymorphism Demonstration</h3>\n" +
               "            <button onclick='demonstratePolymorphism()'>Show Polymorphism</button>\n" +
               "            <div id='polymorphismResult' class='result'></div>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='section'>\n" +
               "            <h3>4. Send Notifications (Method Overriding Demo)</h3>\n" +
               "            <div>\n" +
               "                <select id='notifyUserId'><option value=''>Select User</option></select>\n" +
               "                <input type='text' id='notifyMessage' placeholder='Notification Message' />\n" +
               "                <button onclick='sendNotification()'>Send Notification</button>\n" +
               "            </div>\n" +
               "            <div id='notifyResult' class='result'></div>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='section'>\n" +
               "            <h3>5. Admin Actions Demo</h3>\n" +
               "            <div>\n" +
               "                <select id='adminUserId'><option value=''>Select Admin</option></select>\n" +
               "                <select id='targetUserId'><option value=''>Select Target User</option></select>\n" +
               "                <select id='adminAction'>\n" +
               "                    <option value='suspend'>Suspend User</option>\n" +
               "                    <option value='activate'>Activate User</option>\n" +
               "                    <option value='notify'>Send Admin Notification</option>\n" +
               "                </select>\n" +
               "                <button onclick='performAdminAction()'>Perform Action</button>\n" +
               "            </div>\n" +
               "            <div id='adminResult' class='result'></div>\n" +
               "        </div>\n" +
               "    </div>\n" +
               "    \n" +
               "    <script>\n" +
               "        let users = [];\n" +
               "        \n" +
               "        async function createRegularUser() {\n" +
               "            const name = document.getElementById('userName').value;\n" +
               "            const email = document.getElementById('userEmail').value;\n" +
               "            \n" +
               "            if (!name || !email) {\n" +
               "                document.getElementById('createResult').innerHTML = '<span class=\"error\">Please fill in all fields</span>';\n" +
               "                return;\n" +
               "            }\n" +
               "            \n" +
               "            try {\n" +
               "                const response = await fetch('/api/users/regular', {\n" +
               "                    method: 'POST',\n" +
               "                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },\n" +
               "                    body: `name=${encodeURIComponent(name)}&email=${encodeURIComponent(email)}`\n" +
               "                });\n" +
               "                const result = await response.json();\n" +
               "                \n" +
               "                if (result.success) {\n" +
               "                    document.getElementById('createResult').innerHTML = `<span class=\"success\">${result.message}</span><br>User ID: ${result.user.userId}`;\n" +
               "                    document.getElementById('userName').value = '';\n" +
               "                    document.getElementById('userEmail').value = '';\n" +
               "                    loadUsers();\n" +
               "                } else {\n" +
               "                    document.getElementById('createResult').innerHTML = `<span class=\"error\">${result.message}</span>`;\n" +
               "                }\n" +
               "            } catch (error) {\n" +
               "                document.getElementById('createResult').innerHTML = `<span class=\"error\">Error: ${error.message}</span>`;\n" +
               "            }\n" +
               "        }\n" +
               "        \n" +
               "        async function createAdminUser() {\n" +
               "            const name = document.getElementById('userName').value;\n" +
               "            const email = document.getElementById('userEmail').value;\n" +
               "            \n" +
               "            if (!name || !email) {\n" +
               "                document.getElementById('createResult').innerHTML = '<span class=\"error\">Please fill in all fields</span>';\n" +
               "                return;\n" +
               "            }\n" +
               "            \n" +
               "            try {\n" +
               "                const response = await fetch('/api/users/admin', {\n" +
               "                    method: 'POST',\n" +
               "                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },\n" +
               "                    body: `name=${encodeURIComponent(name)}&email=${encodeURIComponent(email)}`\n" +
               "                });\n" +
               "                const result = await response.json();\n" +
               "                \n" +
               "                if (result.success) {\n" +
               "                    document.getElementById('createResult').innerHTML = `<span class=\"success\">${result.message}</span><br>User ID: ${result.user.userId}`;\n" +
               "                    document.getElementById('userName').value = '';\n" +
               "                    document.getElementById('userEmail').value = '';\n" +
               "                    loadUsers();\n" +
               "                } else {\n" +
               "                    document.getElementById('createResult').innerHTML = `<span class=\"error\">${result.message}</span>`;\n" +
               "                }\n" +
               "            } catch (error) {\n" +
               "                document.getElementById('createResult').innerHTML = `<span class=\"error\">Error: ${error.message}</span>`;\n" +
               "            }\n" +
               "        }\n" +
               "        \n" +
               "        async function loadUsers() {\n" +
               "            try {\n" +
               "                const response = await fetch('/api/users/list');\n" +
               "                const result = await response.json();\n" +
               "                \n" +
               "                if (result.success) {\n" +
               "                    users = result.users;\n" +
               "                    let html = `<h4>Total Users: ${result.count}</h4>`;\n" +
               "                    \n" +
               "                    result.users.forEach(user => {\n" +
               "                        html += `<div class='user-card'>\n" +
               "                            <strong>${user.name}</strong> (${user.userType})<br>\n" +
               "                            Email: ${user.email}<br>\n" +
               "                            Access Level: ${user.accessLevel}<br>\n" +
               "                            User ID: ${user.userId}<br>\n" +
               "                            Status: ${user.status}\n" +
               "                        </div>`;\n" +
               "                    });\n" +
               "                    \n" +
               "                    document.getElementById('usersList').innerHTML = html;\n" +
               "                    updateUserSelects();\n" +
               "                } else {\n" +
               "                    document.getElementById('usersList').innerHTML = `<span class=\"error\">${result.message}</span>`;\n" +
               "                }\n" +
               "            } catch (error) {\n" +
               "                document.getElementById('usersList').innerHTML = `<span class=\"error\">Error: ${error.message}</span>`;\n" +
               "            }\n" +
               "        }\n" +
               "        \n" +
               "        async function demonstratePolymorphism() {\n" +
               "            try {\n" +
               "                const response = await fetch('/api/users/polymorphism');\n" +
               "                const result = await response.json();\n" +
               "                \n" +
               "                if (result.success) {\n" +
               "                    let html = `<h4>${result.message}</h4>`;\n" +
               "                    \n" +
               "                    result.demonstrations.forEach(demo => {\n" +
               "                        html += `<div class='user-card'>\n" +
               "                            <strong>${demo.name}</strong> (${demo.userType})<br>\n" +
               "                            Access Level: <em>${demo.accessLevel}</em> ← Polymorphic call<br>\n" +
               "                            Account Info: ${demo.accountInfo}\n" +
               "                        </div>`;\n" +
               "                    });\n" +
               "                    \n" +
               "                    document.getElementById('polymorphismResult').innerHTML = html;\n" +
               "                } else {\n" +
               "                    document.getElementById('polymorphismResult').innerHTML = `<span class=\"error\">${result.message}</span>`;\n" +
               "                }\n" +
               "            } catch (error) {\n" +
               "                document.getElementById('polymorphismResult').innerHTML = `<span class=\"error\">Error: ${error.message}</span>`;\n" +
               "            }\n" +
               "        }\n" +
               "        \n" +
               "        async function sendNotification() {\n" +
               "            const userId = document.getElementById('notifyUserId').value;\n" +
               "            const message = document.getElementById('notifyMessage').value;\n" +
               "            \n" +
               "            if (!userId || !message) {\n" +
               "                document.getElementById('notifyResult').innerHTML = '<span class=\"error\">Please select user and enter message</span>';\n" +
               "                return;\n" +
               "            }\n" +
               "            \n" +
               "            try {\n" +
               "                const response = await fetch('/api/users/notify', {\n" +
               "                    method: 'POST',\n" +
               "                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },\n" +
               "                    body: `userId=${encodeURIComponent(userId)}&message=${encodeURIComponent(message)}`\n" +
               "                });\n" +
               "                const result = await response.json();\n" +
               "                \n" +
               "                if (result.success) {\n" +
               "                    document.getElementById('notifyResult').innerHTML = `<span class=\"success\">${result.message}</span><br>User Type: ${result.userType} (shows method overriding)`;\n" +
               "                    document.getElementById('notifyMessage').value = '';\n" +
               "                } else {\n" +
               "                    document.getElementById('notifyResult').innerHTML = `<span class=\"error\">${result.message}</span>`;\n" +
               "                }\n" +
               "            } catch (error) {\n" +
               "                document.getElementById('notifyResult').innerHTML = `<span class=\"error\">Error: ${error.message}</span>`;\n" +
               "            }\n" +
               "        }\n" +
               "        \n" +
               "        async function performAdminAction() {\n" +
               "            const adminId = document.getElementById('adminUserId').value;\n" +
               "            const targetUserId = document.getElementById('targetUserId').value;\n" +
               "            const action = document.getElementById('adminAction').value;\n" +
               "            \n" +
               "            if (!adminId || !targetUserId) {\n" +
               "                document.getElementById('adminResult').innerHTML = '<span class=\"error\">Please select admin and target user</span>';\n" +
               "                return;\n" +
               "            }\n" +
               "            \n" +
               "            try {\n" +
               "                const response = await fetch('/api/users/admin-action', {\n" +
               "                    method: 'POST',\n" +
               "                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },\n" +
               "                    body: `adminId=${encodeURIComponent(adminId)}&targetUserId=${encodeURIComponent(targetUserId)}&action=${encodeURIComponent(action)}`\n" +
               "                });\n" +
               "                const result = await response.json();\n" +
               "                \n" +
               "                if (result.success) {\n" +
               "                    document.getElementById('adminResult').innerHTML = `<span class=\"success\">${result.message}</span><br>Action: ${result.action}<br>Admin: ${result.adminName}<br>Target: ${result.targetUserName}`;\n" +
               "                } else {\n" +
               "                    document.getElementById('adminResult').innerHTML = `<span class=\"error\">${result.message}</span>`;\n" +
               "                }\n" +
               "            } catch (error) {\n" +
               "                document.getElementById('adminResult').innerHTML = `<span class=\"error\">Error: ${error.message}</span>`;\n" +
               "            }\n" +
               "        }\n" +
               "        \n" +
               "        function updateUserSelects() {\n" +
               "            const notifySelect = document.getElementById('notifyUserId');\n" +
               "            const adminSelect = document.getElementById('adminUserId');\n" +
               "            const targetSelect = document.getElementById('targetUserId');\n" +
               "            \n" +
               "            // Clear existing options\n" +
               "            notifySelect.innerHTML = '<option value=\"\">Select User</option>';\n" +
               "            adminSelect.innerHTML = '<option value=\"\">Select Admin</option>';\n" +
               "            targetSelect.innerHTML = '<option value=\"\">Select Target User</option>';\n" +
               "            \n" +
               "            users.forEach(user => {\n" +
               "                const option = `<option value=\"${user.userId}\">${user.name} (${user.userType})</option>`;\n" +
               "                notifySelect.innerHTML += option;\n" +
               "                targetSelect.innerHTML += option;\n" +
               "                \n" +
               "                if (user.userType === 'AdminUser') {\n" +
               "                    adminSelect.innerHTML += option;\n" +
               "                }\n" +
               "            });\n" +
               "        }\n" +
               "        \n" +
               "        // Load users on page load\n" +
               "        window.onload = function() {\n" +
               "            loadUsers();\n" +
               "        };\n" +
               "    </script>\n" +
               "</body>\n" +
               "</html>";
    }
    
    public static void main(String[] args) {
        try {
            SimpleWebServer server = new SimpleWebServer(8082);
            server.start();
            
            System.out.println("\nPress Enter to stop the server...");
            System.in.read();
            
            server.stop();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}