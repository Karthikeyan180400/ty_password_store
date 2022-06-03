package com.ty.passwordstore.service;

import com.ty.passwordstore.dao.DetailsDao;
import com.ty.passwordstore.dto.Details;

public class DetailsService {
	DetailsDao detailsDao = new DetailsDao();

	public Details saveDetails(Details details) {
		return detailsDao.saveDetails(details);

	}

}
