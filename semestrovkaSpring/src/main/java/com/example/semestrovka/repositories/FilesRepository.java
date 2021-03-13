package com.example.semestrovka.repositories;

import com.example.semestrovka.models.UploadableFile;
import org.springframework.data.repository.CrudRepository;

public interface FilesRepository extends CrudRepository<UploadableFile, Long> {
}
