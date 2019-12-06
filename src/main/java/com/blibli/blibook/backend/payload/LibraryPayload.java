package com.blibli.blibook.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibraryPayload {

    private String productName;
    private String productAuthor;
    private String productDescription;

}
