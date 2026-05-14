# Spring AI Chatbot

A console-based chatbot built with Java, Spring Boot, and Spring AI (OpenAI GPT-3.5-turbo).

## How It Works

1. **User types a question** in the console
2. **REST data is fetched** from an API based on keywords in the question (users, posts, comments, todos, albums)
3. **AI model answers** the question using the fetched data as context

## Prerequisites

- Java 17+
- Maven
- OpenAI API Key

## Configuration

Set your OpenAI API key in `src/main/resources/application.properties`:

```properties
spring.ai.openai.api-key=your-api-key-here
```

Or set it as an environment variable:

```bash
set OPENAI_API_KEY=sk-your-key-here
```

## REST Data Source

By default, the app uses [JSONPlaceholder](https://jsonplaceholder.typicode.com) as the REST API. You can change `app.rest.base-url` in `application.properties` to point to your own API.

**Supported question keywords → endpoints:**
- "user" / "people" → `/users`
- "post" / "article" / "blog" → `/posts`
- "comment" → `/comments`
- "todo" / "task" → `/todos`
- "album" / "photo" → `/albums`

## Run

```bash
mvn spring-boot:run
```

Then type your questions in the console. Type `exit` to quit.

## Example

```
You: How many users are there?
Bot: Based on the data, there are 10 users...

You: Show me the latest posts
Bot: Here are the recent posts...
```
