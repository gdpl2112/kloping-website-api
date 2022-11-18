package io.github.kloping.mywebsite.entitys.yuanShen;

/**
 * @author HRS-Computer
 */
public class Data {
	private WorldExplorations[] world_explorations;
	private Role role;
	private Stats stats;
	private Homes[] homes;
	private Avatars[] avatars;

	public WorldExplorations[] getWorld_explorations(){
		return this.world_explorations;
	}

	public Data setWorld_explorations(WorldExplorations[] world_explorations) {
		this.world_explorations = world_explorations;
		return this;
	}

	public Role getRole(){
		return this.role;
	}

	public Data setRole(Role role) {
		this.role = role;
		return this;
	}

	public Stats getStats(){
		return this.stats;
	}

	public Data setStats(Stats stats) {
		this.stats = stats;
		return this;
	}

	public Homes[] getHomes(){
		return this.homes;
	}

	public Data setHomes(Homes[] homes) {
		this.homes = homes;
		return this;
	}

	public Avatars[] getAvatars(){
		return this.avatars;
	}

	public Data setAvatars(Avatars[] avatars) {
		this.avatars = avatars;
		return this;
	}
}