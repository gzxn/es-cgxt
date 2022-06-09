<template>
	<el-cascader style="width: 100%;" :clearable="clearable" :show-all-levels="showAllLevels"
		:collapse-tags="collapseTags" :filterable="filterable" :props="propOptions" v-model="value"
		:options="cascaderData" :disabled="disabled" @expand-change="expandChange" @blur="blur" @focus="focus"
		@visible-change="visibleChange" :placeholder="placeholder" @remove-tag="removeTag" ref="cascader"></el-cascader>
</template>

<script>
	import utils from 'util/utils.js';
	export default {
		model: {
			prop: 'checkValue',
			event: "change"
		},
		props: {
			// 是否禁用
			disabled: {
				type: Boolean,
				default () {
					return false;
				}
			},
			/**
			 * 提示
			 */
			placeholder: {
				type: String,
				required: false,
				default: "请选择"
			},
			//数据类型  simple-简单数据、standard-标准
			dataMode: {
				type: String,
				default () {
					return "simple";
				}
			},
			//数据转换原数据的id名
			id: {
				type: String,
				default () {
					return 'id';
				}
			},
			//数据转换原数据的pid名
			pid: {
				type: String,
				default () {
					return 'pid';
				}
			},
			//数据转换原数据的label名
			label: {
				type: String,
				default () {
					return 'name';
				}
			},
			//数据转换原数据的defaultParent名
			defaultParent: {
				type: String | Number,
				default () {
					return null;
				}
			},
			// 是否可清空
			clearable: {
				type: Boolean,
				default () {
					return true;
				}
			},
			// 是否显示完整的路径
			showAllLevels: {
				type: Boolean,
				default () {
					return true;
				}
			},
			// 是否折叠(开启多选模式后可设置)
			collapseTags: {
				type: Boolean,
				default () {
					return false;
				}
			},
			//是否开启搜索功能
			filterable: {
				type: Boolean,
				default () {
					return true;
				}
			},
			// 数据源
			options: {
				type: Array,
				default () {
					return [];
				}
			},
			//返回的层级
			limitLevel: {
				type: Number,
				default () {
					return 3;
				}
			},
			// 回显选中的值
			checkValue: {
				type: Array,
				default () {
					return [];
				}
			},
			//返回选中节点的所有信息
			getAllInfo: {
				type: Boolean,
				default () {
					return false;
				}
			},
			// 属性选项
			propOptions: {
				type: Object,
				default () {
					return {
						multiple: false, //是否多选
						checkStrictly: false, //是否选择任意一级选项
						value: "id", //指定选项的值为选项对象的某个属性值
					};
				}
			}
		},
		data() {
			return {
				value: [], //选中的值
				cascaderData: [] //过滤后的数据源
			}
		},
		methods: {
			// change(val) { 
			// 	//选中
			// 	if (this.getAllInfo) {
			// 		this.$emit("change", val)
			// 	} else {
			// 		let res = this.getAllInfoHandler(val, this.cascaderData)
			// 		this.$emit("change", res)
			// 	}
			// },
			getCheckedObj() {
				return this.getAllInfoHandler(this.checkValue, this.cascaderData)
			},
			// 转换成树形结构数据
			SimpleTreeToStandardTree(tmp) {
				let res = utils.convertSimpleTreeToStandardTree2(
					tmp, {
						id: this.id,
						pid: this.pid,
						defaultParent: this.defaultParent,
						label: this.label
					}, {
						label: 'label'
					}
				);
				return res
			},
			// 过滤数据显示层级
			filterData(count, data, maxNum) {
				if (count >= maxNum) { //当前层级大于最大限制数
					data.forEach(item => {
						delete item.children
					})
				} else {
					data.forEach(item => {
						item.count = count // 计数器

						if (item.children && item.children.length) {
							item.count++
							this.filterData(item.count, item.children, maxNum) //递归
						}
					})
				}
				return data
			},
			// 返回选中节点的所有信息
			getAllInfoHandler(idArray, dataArray) {
				let arr = []

				function getAllInfoHandlerInner(idArray, dataArray) {
					if (dataArray && dataArray.length) {
						idArray.forEach((items, idxs) => {
							dataArray.forEach((item) => {
								if (items === item.id) {
									arr.push(item)
									getAllInfoHandlerInner(idArray, item.children)
								}
							})
						})
					}
				}
				getAllInfoHandlerInner(idArray, dataArray)
				return arr
			},
			// 当展开节点发生变化时触发 回调：各父级选项值组成的数组
			expandChange(val) {
				this.$emit("expand-change", val)
			},
			// 当失去焦点时触发 回调：event
			blur(event) {
				this.$emit("blur", event)
			},
			// 当获得焦点时触发 回调：event
			focus(event) {
				this.$emit("focus", event)
			},
			// 下拉框出现/隐藏时触发 回调：出现则为 true，隐藏则为 false
			visibleChange(val) {
				this.$emit("visible-change", val)
			},
			// 在多选模式下，移除Tag时触发 回调：移除的Tag对应的节点的值
			removeTag(val) {
				this.$emit("remove-tag", val)
			},
			// 获取选中的节点 回调：(leafOnly) 是否只是叶子节点，默认值为 false
			getCheckedNodes() {
				return this.$refs.cascader.getCheckedNodes()
			}
		},
		watch: {
			//监听传入进来的数据源
			options: {
				handler(val) {
					if (val && val.length) {
						if (this.dataMode === "simple") {
							let res = this.SimpleTreeToStandardTree(val)
							this.cascaderData = this.filterData(1, res, this.limitLevel)
						} else {
							this.cascaderData = this.filterData(1, val, this.limitLevel)
						}
					}
				},
				immediate: true,
				deep: true
			},
			//监听选中值，让父级组件通过v-model获取选中数据
			value: {
				handler(val) {
					this.$emit("change", val)
				},
				immediate: true,
				deep: true
			},
			//监听回调显示数据
			checkValue: {
				handler(val) {
					this.value = val
				},
				immediate: true,
				deep: true
			}
		}
	}
</script>

<style>
</style>
