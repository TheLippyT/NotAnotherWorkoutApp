package com.example.notanotherworkoutapp;

class QuoteDto {
    private String content;
    private String author;

    public QuoteDto(String content, String author) {
        this.content = content;
        this.author = author;

    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "QuotesDto{" +
                "content" + content + '\'' +
                ", author " + author + '\'' +
                '}';
    }
}