export type Category = {
    id: number,
    parentCategoryId: number,
    categoryName: string,
    slug: string,
    description: string,
    sortOrder: number,
    depth: number,
    useYn: string,
    createdAt: Date,
    updatedAt: Date,
    childrenList: Array<Category>,
}