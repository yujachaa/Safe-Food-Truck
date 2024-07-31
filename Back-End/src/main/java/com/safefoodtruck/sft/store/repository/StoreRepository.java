package com.safefoodtruck.sft.store.repository;

import com.safefoodtruck.sft.member.domain.Member;
import com.safefoodtruck.sft.store.domain.Store;
import com.safefoodtruck.sft.store.dto.response.StoreInfoResponseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	Optional<Store> findByOwner(Member member);

	@Query("SELECT s.id FROM Store s WHERE s.owner.email = :email")
	Optional<Integer> findStoreIdByOwnerEmail(@Param("email") String email);

	@Query("SELECT new com.safefoodtruck.sft.store.dto.response.StoreInfoResponseDto(s.id, s.name, s.latitude, s.longitude) FROM Store s WHERE s.isOpen = true")
	List<StoreInfoResponseDto> findOpenStores();
}
