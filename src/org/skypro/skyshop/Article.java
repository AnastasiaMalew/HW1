package org.skypro.skyshop;

public class Article implements Searchable {

    private final String nameArticle;
    private final String textArticle;

    public Article(String nameArticle, String textArticle) {
        if (nameArticle == null || textArticle == null) {
            throw new IllegalArgumentException("Название и текст статьи отсутствует");
        }
        this.nameArticle =nameArticle;
        this.textArticle =textArticle;
        }

    public String getNameArticle() {
        return nameArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    @Override
    public String getSearchTerm() {
        return nameArticle + "\n" + textArticle;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return nameArticle;
    }

    @Override
    public String toString() {
        return nameArticle + "\n" + textArticle;
    }
}
