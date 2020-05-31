export interface MenuItem {

    name: string;

    url: string;

}

export interface MenuGroup {

    header: MenuItem

    items: MenuItem[]

}
