USE `webshop_quan_ao`;

create table mau_sac(
	id bigint primary key,
    ten varchar(20)
);

create table kich_co(
	id bigint primary key,
    ten varchar(20)
);

create table loai_san_pham(
	id bigint primary key,
    ten varchar(20)
);

create table san_pham(
	id bigint primary key,
    ma varchar(20),
    ten varchar(150),
    gia bigint,
    so_luong_ton_kho bigint,
    so_luong_da_ban bigint,
    mo_ta varchar(255),
    trang_thai int,
    loai_san_pham_id bigint,
    foreign key (loai_san_pham_id) references loai_san_pham (id)
);

create table anh_san_pham(
	san_pham_id bigint,
    images varchar(255),
    foreign key (san_pham_id) references san_pham (id)
);

create table san_pham_chi_tiet (
	id bigint primary key,
    ma varchar(255),
    ten varchar(255),
    gia bigint,
    so_luong_ton_kho bigint,
    so_luong_da_ban bigint,
    trang_thai int,
    san_pham_id bigint,
    mau_sac_id bigint,
    kich_co_id bigint, 
    foreign key (san_pham_id) references san_pham(id),
    foreign key (mau_sac_id) references mau_sac(id),
    foreign key (kich_co_id) references kich_co(id)
); 

