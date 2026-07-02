import {getCategories} from "@/src/features/categories/api";
import {CategoryDashboard} from "@/src/features/categories/ui";

export default async function CategoryPage() {

    const categories = await getCategories();

    return <CategoryDashboard categories={categories}/>
}