
const categoryModal = document.querySelector('#category-modal');
const categoryModalShowButton = document.querySelector('#category-modal-show-button');
const categoryModalCancelButton = document.querySelector('#category-modal-cancel-button');
const categoryForm = document.querySelector('#category-form');
const categoryIconContainer = document.querySelector('#category-icon-container');
const selectedCategoryIcon = document.querySelector('#selected-category-icon');
const categoryColorContainer = document.querySelector('#category-color-container');
const selectedCategoryColor = document.querySelector('#selected-category-color');

const init = () => {
    categoryModalShowButton.addEventListener('click', showCategoryModal);
    categoryModalCancelButton.addEventListener('click', hideCategoryModal);
    categoryForm.addEventListener('submit', submitCategory);
    categoryIconContainer.addEventListener('click', selectCategoryIcon);
    categoryColorContainer.addEventListener('click', selectCategoryColor);
}

// 카테고리 모달 보이기
const showCategoryModal = () => {
    categoryModal.classList.remove('invisible', 'opacity-0');
    categoryModal.classList.add('visible', 'opacity-100');
}

// 카테고리 모달 숨기기
const hideCategoryModal = () => {
    categoryModal.classList.remove('visible', 'opacity-100');
    categoryModal.classList.add('invisible', 'opacity-0');
}

// 카테고리 아이콘 선택 이벤트
const selectCategoryIcon = (e) => {
    if (e.target.tagName !== 'BUTTON') {
        return;
    }

    categoryIconContainer.querySelectorAll('button').forEach(button => {
        button.classList.replace('bg-indigo-100', 'bg-gray-50');
        button.classList.replace('border-2', 'border');
        button.classList.replace('border-indigo-500', 'border-gray-200');
        button.classList.add('hover:border-gray-300');
    })

    e.target.classList.replace('bg-gray-50', 'bg-indigo-100');
    e.target.classList.replace('border', 'border-2');
    e.target.classList.replace('border-gray-200', 'border-indigo-500');
    e.target.classList.remove('hover:border-gray-300');

    selectedCategoryIcon.value = e.target.innerText;
}

// 카테고리 색상 선택 이벤트
const selectCategoryColor = (e) => {
    if (e.target.tagName !== 'BUTTON') {
        return;
    }

    categoryColorContainer.querySelectorAll('button').forEach(button => {
        button.classList.remove('ring-2', 'ring-offset-2', 'ring-indigo-500', 'scale-110');
    })

    e.target.classList.add('ring-2', 'ring-offset-2', 'ring-indigo-500', 'scale-110');

    const pickedColor = e.target.style.backgroundColor;
    const result = pickedColor.match(/\d+/g);
    const r = parseInt(result[0]).toString(16).padStart(2, '0');
    const g = parseInt(result[1]).toString(16).padStart(2, '0');
    const b = parseInt(result[2]).toString(16).padStart(2, '0');

    selectedCategoryColor.value = `#${r}${g}${b}`;
}

const submitCategory = (e) => {
    e.preventDefault();
    console.log(e);
}

document.addEventListener('DOMContentLoaded', init);