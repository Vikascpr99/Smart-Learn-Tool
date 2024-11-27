package com.smartlearntool.SmartLearnTool.Repositories;

import com.smartlearntool.SmartLearnTool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String>
{

}
