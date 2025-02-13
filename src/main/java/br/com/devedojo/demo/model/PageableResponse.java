package br.com.devedojo.demo.model;
import br.com.devedojo.demo.util.CustomSortDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"pageable"})
@Getter
@Setter
public class PageableResponse<T> extends PageImpl<T> {
    private boolean last;
    private boolean first;
    private int totalPages;

    public PageableResponse(@JsonProperty("content") List<T> content,
                            @JsonProperty("number") int page,
                            @JsonProperty("size") int size,
                            @JsonProperty("totalElements") long totalElements,
                            @JsonProperty("sort") @JsonDeserialize(using = CustomSortDeserializer.class)
                            Sort sort) {
        super(content, PageRequest.of(page, size, sort), totalElements);
    }

    public PageableResponse() {
        super(new ArrayList<>());
    }

    @Override
    public boolean isLast() {
        return last;
    }

    @Override
    public boolean isFirst() {
        return first;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }
}
