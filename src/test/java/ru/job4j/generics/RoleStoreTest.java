package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFind() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRolename()).isEqualTo("doctor");
    }

    @Test
    void whenAddDuplicate() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        store.add(new Role("1", "driver"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRolename()).isEqualTo("doctor");
    }

    @Test
    void whenFindIfAbsent() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        Role rsl = store.findById("3");
        assertThat(rsl).isNull();
    }

    @Test
    void whenReplaceExisting() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        store.replace("1", new Role("1", "driver"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRolename()).isEqualTo("driver");
    }

    @Test
    void whenReplaceExistingIsTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        assertThat(store.replace("1", new Role("1", "driver"))).isTrue();
    }

    @Test
    void whenReplaceNotExisting() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        store.replace("2", new Role("1", "driver"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRolename()).isEqualTo("doctor");
    }

    @Test
    void whenReplaceNotExistingIsFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        assertThat(store.replace("2", new Role("1", "driver"))).isFalse();
    }

    @Test
    void whenDeleteExisting() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        store.delete("1");
        Role rsl = store.findById("1");
        assertThat(rsl).isNull();
    }

    @Test
    void whenDeleteExistingIsTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        assertThat(store.delete("1")).isTrue();
    }

    @Test
    void whenDeleteNotExisting() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        store.delete("2");
        Role rsl = store.findById("1");
        assertThat(rsl.getRolename()).isEqualTo("doctor");
    }

    @Test
    void whenDeleteNotExistingIsFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "doctor"));
        assertThat(store.delete("2")).isFalse();
    }

}