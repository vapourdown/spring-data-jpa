/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jpa.repository.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Version;

class EntityManagerFactoryProducer {

	@Produces
	@ApplicationScoped
	public EntityManagerFactory createEntityManagerFactory() {

		String hibernateVersion = Version.getVersionString();
		return Persistence.createEntityManagerFactory(hibernateVersion.startsWith("5.2") ? "cdi-52" : "cdi");
	}

	public void close(@Disposes EntityManagerFactory entityManagerFactory) {
		entityManagerFactory.close();
	}
}
