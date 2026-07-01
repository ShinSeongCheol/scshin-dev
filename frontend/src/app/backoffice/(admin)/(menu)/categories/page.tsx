import {getCategories} from "@/src/features/backoffice/category/api";
import {CategoryDashboard} from "@/src/features/backoffice/category/ui";

export default async function CategoryPage() {

    const categories = await getCategories();

    return <CategoryDashboard categories={categories}/>
}