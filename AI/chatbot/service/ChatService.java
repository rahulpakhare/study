// ...existing code...
    /**
     * Processes the user question: fetches data via REST, then asks the AI model
     * to answer the question using that data as context.
     */
    public String ask(String question) {
        // Step 1: Fetch relevant data from REST services
        String contextData = restDataService.fetchData(question);

        // Step 2: Determine response format based on the question
        String responseFormat = determineResponseFormat(question);
        System.out.println("responseFormat " + responseFormat);
        // Step 3: Build prompt with context, question, and dynamic response format
        String prompt = """
                You are a helpful assistant. Use the following data to answer the user's question.
                If the data doesn't contain enough information, say so politely.

                --- DATA ---
                %s
                --- END DATA ---

                User's Question: %s

                Response format:
                %s

                Please provide a clear and concise answer based on the data above. Stictly forllow response format and do not use any other format.
                """.formatted(contextData, question, responseFormat);

        // Step 4: Get AI response
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    /**
     * Determines the response format based on the user's question.
     * Extend this method to handle more cases as needed.
     */
    private String determineResponseFormat(String question) {
        // Example: simple keyword-based logic
        if (question.toLowerCase().contains("code")) {
            System.out.println("inside code repsone format");
            return "Initiative  pi fpc id   status work\n1234    3445    Open-FailedValidation";
        }
        // Default format
        return "Add intiative id and requested column names and data in a table format. Do not use previous question resposne format";
    }
// ...existing code...
