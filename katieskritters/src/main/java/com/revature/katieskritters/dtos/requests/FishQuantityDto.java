package com.revature.katieskritters.dtos.requests;

import com.revature.katieskritters.entities.Fish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishQuantityDto {
    private Fish fish;
    private int quantity;
}
