package com.kindle.backend.response.relationshipResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionListRelationshipResponse<T> {
  private T merchant;
  private T book;
}
