
package com.devhp.paging_lib_java_sample.model.entites;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieDBResponse {

    @Expose
    private Long page;
    @Expose
    @SerializedName("results")
    private List<Movie> movies;
    @SerializedName("total_pages")
    private Long totalPages;
    @SerializedName("total_results")
    private Long totalResults;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return movies;
    }

    public void setResults(List<Movie> movies) {
        this.movies = movies;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

}
