package com.proj.donemcd.repository;

import com.proj.donemcd.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {




}
