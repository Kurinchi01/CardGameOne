package com.kuri01.Game.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerActionQueueDTO {
    List<PlayerActionDTO> playerActionDTOList = new ArrayList<>();

}
