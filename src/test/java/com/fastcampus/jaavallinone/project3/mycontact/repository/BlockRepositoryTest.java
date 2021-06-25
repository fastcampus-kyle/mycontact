package com.fastcampus.jaavallinone.project3.mycontact.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jaavallinone.project3.mycontact.domain.Block;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BlockRepositoryTest {

  @Autowired
  private BlockRepository blockRepository;

  @Test
  void crud(){
    Block block = new Block();
    block.setName("martin");
    block.setReason("친하지않아서");
    block.setStartDate(LocalDate.now());
    block.setEndDate(LocalDate.now());

    blockRepository.save(block);

    List<Block> blocks = blockRepository.findAll();

    assertThat(blocks.size()).isEqualTo(1);
    assertThat(blocks.get(0).getName()).isEqualTo("martin");
  }

}