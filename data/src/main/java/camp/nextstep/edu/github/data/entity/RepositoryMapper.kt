package camp.nextstep.edu.github.data.entity

import camp.nextstep.edu.github.domain.repository.Repository

internal class RepositoryMapper : Mapper<RepositoryEntity, Repository> {

    override fun toDomain(entity: RepositoryEntity): Repository =
        Repository(
            fullName = entity.fullName.orEmpty(),
            description = entity.description.orEmpty()
        )

    override fun toEntity(domain: Repository): RepositoryEntity =
        RepositoryEntity(
            fullName = domain.fullName,
            description = domain.description
        )
}