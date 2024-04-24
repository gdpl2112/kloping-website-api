package io.github.kloping.mywebsite.github.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GithubUser {
    private String gists_url;
    private String repos_url;
    private Boolean two_factor_authentication;
    private String following_url;
    private String bio;
    private String created_at;
    private String login;
    private String type;
    private String blog;
    private Number private_gists;
    private Number total_private_repos;
    private String subscriptions_url;
    private String updated_at;
    private Boolean site_admin;
    private Number disk_usage;
    private Number collaborators;
    private String company;
    private Number owned_private_repos;
    private Number id;
    private Number public_repos;
    private String gravatar_id;
    private Plan plan;
    private String organizations_url;
    private String starred_url;
    private String followers_url;
    private Number public_gists;
    private String url;
    private String received_events_url;
    private Number followers;
    private String avatar_url;
    private String events_url;
    private String html_url;
    private Number following;
    private String name;
    private String location;
    private String node_id;


    @Data
    @Accessors(chain = true)
    public static class Plan {
        private Number private_repos;
        private String name;
        private Number collaborators;
        private Number space;

    }
}