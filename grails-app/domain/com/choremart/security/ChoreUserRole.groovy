package com.choremart.security

import org.apache.commons.lang.builder.HashCodeBuilder

class ChoreUserRole implements Serializable {

	private static final long serialVersionUID = 1

	ChoreUser choreUser
	Role role

	boolean equals(other) {
		if (!(other instanceof ChoreUserRole)) {
			return false
		}

		other.choreUser?.id == choreUser?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (choreUser) builder.append(choreUser.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static ChoreUserRole get(long choreUserId, long roleId) {
		ChoreUserRole.where {
			choreUser == ChoreUser.load(choreUserId) &&
			role == Role.load(roleId)
		}.get()
	}

	static ChoreUserRole create(ChoreUser choreUser, Role role, boolean flush = false) {
		new ChoreUserRole(choreUser: choreUser, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(ChoreUser u, Role r, boolean flush = false) {

		int rowCount = ChoreUserRole.where {
			choreUser == ChoreUser.load(u.id) &&
			role == Role.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(ChoreUser u) {
		ChoreUserRole.where {
			choreUser == ChoreUser.load(u.id)
		}.deleteAll()
	}

	static void removeAll(Role r) {
		ChoreUserRole.where {
			role == Role.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['role', 'choreUser']
		version false
	}
}
